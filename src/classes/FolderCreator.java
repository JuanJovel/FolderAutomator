package classes;

import java.io.File;

/**
 * A class that creates directories.
 *
 * @author Juan Jovel (www.github.com)
 */
public class FolderCreator {
    private String path;
    private String folderName;
    private boolean parentFolderCreated = false;

    public FolderCreator(String path, String folderName) {
        this.path = path;
        this.folderName = folderName;
    }


    public boolean createParentFolder() {
        // Creates a file object in the specified path.
        File file = new File(this.path + "\\" + this.folderName);

        // Determines whether the creation of the directory was successful or
        // not.
        boolean successful;

        // Try-catch block catches any SecurityExceptions that might be thrown
        // when attempting to create directory.
        try {
            successful = file.mkdir();
        }
        catch (SecurityException e) {
            successful = false;
        }

        // Set flag to the result of making the directory.
        this.parentFolderCreated = successful;

        // Returns true if the directory was successfully created, false
        // otherwise.
        return successful;

    }


    public String getFolderPath() {
        String folderPath = this.path + "\\" + this.folderName;
        return folderPath;
    }


    public boolean createChildFolder(String childFolderName) {
        if (!this.parentFolderCreated) {
            throw new IllegalStateException(
                "Parent folder must be created before creating child folders.");
        }

        File file = new File(this.getFolderPath() + "\\" + childFolderName);

        boolean successful;

        try {
            successful = file.mkdir();
        }
        catch (SecurityException e) {
            successful = false;
        }

        return successful;
    }

}
