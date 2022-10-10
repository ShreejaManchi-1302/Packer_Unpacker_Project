import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Arrays;

public class MarvellousPacker
{
   FileOutputStream outstream=null;
    String folder;
   String ValidExt[]={".txt",".c",".cpp",".java"};
    
    public MarvellousPacker(String src,String dest)throws Exception
     {
        String Magic="Marvellous11";
         byte arr[]=Magic.getBytes();
         outstream=new FileOutputStream(dest);
         outstream.write(arr,0,arr.length);
        folder=src;

        System.setProperty("user.dir",src);
       
          ListAllFiles(src);
      }
   
      public void ListAllFiles(String path)
    {
      try(Stream<Path>paths=Files.walk(Paths.get(path)))
       {
         paths.forEach(filePath->
             {
                if(Files.isRegularFile(filePath))
                   {
                     try
                      {
                         String name=filePath.getFileName().toString();
                         String k=name.split("/.")[0]+"."+name.split("/.")[1];
                         String ext=name.substring(name.lastIndexOf("."));
                         List<String>list=Arrays.asList(ValidExt);
                         
                            if(list.contains(ext))
                             {
                                File file=new File(k);
                                String r = file.getAbsolutePath();
                                String s=folder+"/"+k;
                                String cnt=r.replace(k,s);
                                Pack(cnt);
                             }
                      }
                      catch(Exception e)
                      {
                          System.out.println(e+" NOT_FOUND");
                      }
                }
           });
   }
  catch(IOException e)
   {
       System.out.println(e+" NF");
    }
}
   
 public void Pack(String filePath)
{ 
    FileInputStream instream=null;
     
    try
   {
       byte[]buffer=new byte[1024];
       int length;
       byte temp[]=new byte[100];
       File fobj=new File(filePath);
       String Header=filePath+" "+fobj.length();
      System.out.println(Header);
       for(int i=Header.length();i<100;i++)
         Header+=" ";
        temp=Header.getBytes();
       instream=new FileInputStream(filePath);
       outstream.write(temp,0,temp.length);
        while((length=instream.read(buffer))>0)
        {
          outstream.write(buffer,0,length);
        }
        instream.close();
      }
      catch(Exception e)
      {
        System.out.println(e+" PACK");
      }
   }
}
