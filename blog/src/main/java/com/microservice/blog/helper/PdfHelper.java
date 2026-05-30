package com.microservice.blog.helper;

import com.github.jknack.handlebars.Handlebars;
import com.github.jknack.handlebars.Template;
import com.github.jknack.handlebars.io.URLTemplateSource;
import com.openhtmltopdf.outputdevice.helper.BaseRendererBuilder;
import com.openhtmltopdf.pdfboxout.PdfRendererBuilder;
import com.openhtmltopdf.svgsupport.BatikSVGDrawer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Objects;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@Component
public class PdfHelper {
    private final Handlebars handlebars;

    private static final String[] ARIAL_FONTS = {
            "fonts/ARIAL.TTF",
            "fonts/ARIALBD.TTF",
            "fonts/ARIALI.TTF",
            "fonts/ARIALBI.TTF"
    };

    private static final int[] WEIGHTS = {400, 700, 400, 700};

    private static final boolean[] ITALICS = {false, false, true, true};

    public byte[] generatePdf(String templatePath, Map<String, Object> data) {

        try {
            Resource resource = new ClassPathResource(templatePath);

            Template template = compileTemplate(resource);

            String html = template.apply(data);

            return renderPdf(html, resource.getURL());

        } catch (Exception e) {
            log.error("Failed to generate PDF from template: {}", templatePath, e);
            throw new RuntimeException("PDF generation failed", e);
        }
    }

    private Template compileTemplate(Resource resource) throws Exception {
        return handlebars.compile(
                new URLTemplateSource(
                        Objects.requireNonNull(resource.getFilename()),
                        resource.getURL()
                )
        );
    }

    private byte[] renderPdf(String html, URL baseUrl) {

        try (ByteArrayOutputStream baos = new ByteArrayOutputStream();
             BatikSVGDrawer svgDrawer = new BatikSVGDrawer()) {

            PdfRendererBuilder builder = new PdfRendererBuilder();

            builder.withHtmlContent(html, baseUrl.toExternalForm());
            builder.useSVGDrawer(svgDrawer);
            builder.useFastMode();

            registerFonts(builder);

            builder.toStream(baos);
            builder.run();

            return baos.toByteArray();

        } catch (Exception e) {
            log.error("Error rendering PDF", e);
            throw new RuntimeException("PDF render failed", e);
        }
    }

    private void registerFonts(PdfRendererBuilder builder) {

        for (int i = 0; i < ARIAL_FONTS.length; i++) {
            try {
                Resource fontResource = new ClassPathResource(ARIAL_FONTS[i]);

                if (!fontResource.exists()) continue;

                InputStream fontStream = fontResource.getInputStream();

                builder.useFont(
                        () -> fontStream,
                        "Arial",
                        WEIGHTS[i],
                        ITALICS[i]
                                ? BaseRendererBuilder.FontStyle.ITALIC
                                : BaseRendererBuilder.FontStyle.NORMAL,
                        true
                );

            } catch (Exception e) {
                log.warn("Cannot load font: {}", ARIAL_FONTS[i], e);
            }
        }
    }
}