package dev.nokee.docs.fixtures.html;

import lombok.Value;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;

@Value
public class BakedHtmlFixture {
	Path root;

	public List<HtmlTestFixture> findAllHtml() {
		List<HtmlTestFixture> result = new ArrayList<>();
		try {
			Files.walkFileTree(root, new SimpleFileVisitor<Path>() {
				@Override
				public FileVisitResult visitFile(Path path, BasicFileAttributes basicFileAttributes) throws IOException {
					if (isHtmlFile(path)) {
						result.add(new HtmlTestFixture(root, path.toUri(), UriService.INSTANCE));
					}
					return FileVisitResult.CONTINUE;
				}
			});
		} catch (IOException e) {
			throw new UncheckedIOException("Unable to find href containing files", e);
		}
		return result;
	}

	private static boolean isHtmlFile(Path path) {
		return path.getFileName().toString().endsWith(".html");
	}
}