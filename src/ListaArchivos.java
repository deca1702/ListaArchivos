import java.io.File;
import java.util.ArrayList;

public class ListaArchivos {

    public void list(String path, String filter) {
        File dir = new File(path);
        if (!dir.isDirectory()) {
            System.out.println("La ruta proporcionada no es un directorio.");
            return;
        }

        File[] files = dir.listFiles();
        if (files == null) {
            System.out.println("No se pudo acceder al contenido del directorio.");
            return;
        }

        ArrayList<File> subdirs = new ArrayList<>();
        ArrayList<File> regularFiles = new ArrayList<>();

        for (File file : files) {
            if (file.isDirectory()) {
                subdirs.add(file);
            } else {
                regularFiles.add(file);
            }
        }

        switch (filter) {
            case "1":
                // Mostrar primero nombres de subdirectorios y luego ficheros
                System.out.println("Subdirectorios:");
                for (File subdir : subdirs) {
                    System.out.println(subdir.getName());
                }
                System.out.println("\nFicheros:");
                for (File regularFile : regularFiles) {
                    System.out.println(regularFile.getName());
                }
                break;

            case "2":
                // Mostrar permisos, tamaño y formato similar a ls -l
                for (File subdir : subdirs) {
                    System.out.println(getPermissions(subdir) + " " + subdir.length() + " " + subdir.getName());
                }
                for (File regularFile : regularFiles) {
                    System.out.println(getPermissions(regularFile) + " " + regularFile.length() + " " + regularFile.getName());
                }
                System.out.println("\ntotal: ficheros: " + regularFiles.size() + ", subdirectorios: " + subdirs.size());
                break;

            default:
                // Mostrar ficheros que coinciden con el filtro de extensión
                String extension = filter.startsWith(".") ? filter : "." + filter;
                for (File regularFile : regularFiles) {
                    if (regularFile.getName().endsWith(extension)) {
                        System.out.println(regularFile.getName());
                    }
                }
                break;
        }
    }

    private String getPermissions(File file) {
        StringBuilder perms = new StringBuilder();

        perms.append(file.isDirectory() ? "d" : "-");
        perms.append(file.canRead() ? "r" : "-");
        perms.append(file.canWrite() ? "w" : "-");
        perms.append(file.canExecute() ? "x" : "-");

        return perms.toString();
    }

    public static void main(String[] args) {
        ListaArchivos lista = new ListaArchivos();

        // Ejemplo de uso
        lista.list("C:\\Users\\Desi\\source\\repos\\net-projects", "1"); // Cambiar por el path real y filtro deseado
    }
}
