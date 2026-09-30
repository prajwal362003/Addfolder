public void addFolder(Path folder) throws IOException {
    Files.walk(folder)
        .filter(Files::isRegularFile)
        .forEach(file -> {
            try {
                String entryName = folder.relativize(file)
                        .toString()
                        .replace("\\", "/");

                zipOutputStream.putNextEntry(
                    new ZipEntry(entryName)
                );

                Files.copy(file, zipOutputStream);
                zipOutputStream.closeEntry();

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
}
