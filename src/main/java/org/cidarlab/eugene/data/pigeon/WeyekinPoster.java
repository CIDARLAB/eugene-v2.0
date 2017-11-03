/*
 * Copyright (c) 2014, Boston University
 * All rights reserved.
 * 
 * Redistribution and use in source and binary forms, with or 
 * without modification, are permitted provided that the following 
 * conditions are met:
 * 
 * 1. Redistributions of source code must retain the above copyright 
 *    notice, this list of conditions and the following disclaimer.
 *    
 * 2. Redistributions in binary form must reproduce the above copyright 
 *    notice, this list of conditions and the following disclaimer in 
 *    the documentation and/or other materials provided with the distribution.
 *    
 * 3. Neither the name of the copyright holder nor the names of its 
 *    contributors may be used to endorse or promote products derived 
 *    from this software without specific prior written permission.
 *    
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS 
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT 
 * LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR 
 * A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT 
 * HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, 
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED 
 * TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR 
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF 
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING 
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, 
 * EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package org.cidarlab.eugene.data.pigeon;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

/**
 *
 * @author Janoo Fernandes
 */
public class WeyekinPoster {

    //*****WebGraphViz variables*****\\
    private static String mGraphVizString;
    private static String mGraphVizImageIdentifier = "Weyekin output image";
    private static URI mGraphVizURI;
//    private static String mGraphVizPath = "http://128.197.164.27/graphviz/";
    private static String mGraphVizPath = "http://pigeon.synbiotools.org/graphviz/";
    private static String mGraphVizDotText = "digraph { a [shape=pentagon]; a -> b }";
    private static String mGraphVizAspectRatioString="";
    
    
    //*****Pigeon variables**********\\
    private static String mPigeonString;
    private static String mPigeonImageIdentifier = "Weyekin output image";
    private static URI mPigeonURI;
//    private static String mPigeonPath = "http://128.197.164.27/";
    private static String mPigeonPath = "http://pigeon.synbiotools.org/";
    private static String mPigeonText = "| foo \n | bar";
    private static String mPigeonBackgroundColorHexString="";
    
    private static final String PIGEON_URL = mPigeonPath + "pigeon.php";
    private static final String GRAPHVIZ_URL = mGraphVizPath + "graphviz.php";
    
    public static URI postMyVision() {
        DefaultHttpClient httpclient = new DefaultHttpClient();
//        HttpPost httpPost = new HttpPost("http://128.197.164.27/graphviz/graphviz.php");
        HttpPost httpPost = new HttpPost(GRAPHVIZ_URL);
        List<NameValuePair> nvps = new ArrayList<NameValuePair>();
        nvps.add(new BasicNameValuePair("gviz", mGraphVizDotText));
        if (!mGraphVizAspectRatioString.isEmpty()) {
            nvps.add(new BasicNameValuePair("aratio", mGraphVizAspectRatioString));
        }
        try {
            httpPost.setEntity(new UrlEncodedFormEntity(nvps));

            try {
                HttpResponse response2 = httpclient.execute(httpPost);
                try {
//                    System.out.println(response2.getStatusLine());
                    HttpEntity entity2 = response2.getEntity();
//                    entity2.writeTo(System.out);
                    // do something useful with the response body
//                    System.out.println();
                    // and ensure it is fully consumed
                    EntityUtils.consume(entity2);
                } finally {
                    httpPost.releaseConnection();
                }
            } catch (ClientProtocolException ex) {
                Logger.getLogger(WeyekinPoster.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(WeyekinPoster.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(WeyekinPoster.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
//            httpPost.setURI(new URI("http://128.197.164.27/graphviz/graphviz1.php"));
            httpPost.setURI(new URI("http://pigeon.synbiotools.org/graphviz/graphviz1.php"));
            try {
                httpPost.setEntity(new UrlEncodedFormEntity(nvps));
                try {
                    HttpResponse response2 = httpclient.execute(httpPost);
                    try {
                        //System.out.println(response2.getStatusLine());
                        HttpEntity entity2 = response2.getEntity();
                        mGraphVizString = EntityUtils.toString(entity2);
                        entity2.writeTo(System.out);
                        // do something useful with the response body
                        System.out.println();
                        // and ensure it is fully consumed
                        EntityUtils.consume(entity2);
                    } finally {
                        httpPost.releaseConnection();
                    }
                } catch (ClientProtocolException ex) {
                    Logger.getLogger(WeyekinPoster.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(WeyekinPoster.class.getName()).log(Level.SEVERE, null, ex);
                }
            } catch (UnsupportedEncodingException ex) {
                Logger.getLogger(WeyekinPoster.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (URISyntaxException ex) {
            Logger.getLogger(WeyekinPoster.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        if (!mGraphVizString.isEmpty()) {
            return parseGraphVizResponse();
        }
        return null;
    }
    
    public static URI getMyBirdsURL() {
        DefaultHttpClient httpclient = new DefaultHttpClient();
//      HttpPost httpPost = new HttpPost("http://128.197.164.27/pigeon1.php");
      HttpPost httpPost = new HttpPost(PIGEON_URL);
      List<NameValuePair> nvps = new ArrayList<NameValuePair>();
      nvps.add(new BasicNameValuePair("desc", mPigeonText));
      if (!mPigeonBackgroundColorHexString.isEmpty()) {
          nvps.add(new BasicNameValuePair("bgcolor", mPigeonBackgroundColorHexString));
      }
      try {
          httpPost.setEntity(new UrlEncodedFormEntity(nvps));

          try {
              HttpResponse response2 = httpclient.execute(httpPost);
              try {
//                  System.out.println(response2.getStatusLine());
                  HttpEntity entity2 = response2.getEntity();
//                  entity2.writeTo(System.out);
                  // do something useful with the response body
//                  System.out.println();
                  // and ensure it is fully consumed
                  EntityUtils.consume(entity2);
              } finally {
                  httpPost.releaseConnection();
              }
          } catch (ClientProtocolException ex) {
              Logger.getLogger(WeyekinPoster.class.getName()).log(Level.SEVERE, null, ex);
          } catch (IOException ex) {
              Logger.getLogger(WeyekinPoster.class.getName()).log(Level.SEVERE, null, ex);
          }
      } catch (UnsupportedEncodingException ex) {
          Logger.getLogger(WeyekinPoster.class.getName()).log(Level.SEVERE, null, ex);
      }

      try {
//          httpPost.setURI(new URI("http://128.197.164.27/pigeon.php"));
          httpPost.setURI(new URI(PIGEON_URL));
          try {
              httpPost.setEntity(new UrlEncodedFormEntity(nvps));
              try {
                  HttpResponse response2 = httpclient.execute(httpPost);
                  try {
                      //System.out.println(response2.getStatusLine());
                      HttpEntity entity2 = response2.getEntity();
                      mPigeonString = EntityUtils.toString(entity2);
                      entity2.writeTo(System.out);
                      // do something useful with the response body
                      //System.out.println();
                      // and ensure it is fully consumed
                      EntityUtils.consume(entity2);
                  } finally {
                      httpPost.releaseConnection();
                  }
              } catch (ClientProtocolException ex) {
                  Logger.getLogger(WeyekinPoster.class.getName()).log(Level.SEVERE, null, ex);
              } catch (IOException ex) {
                  Logger.getLogger(WeyekinPoster.class.getName()).log(Level.SEVERE, null, ex);
              }
          } catch (UnsupportedEncodingException ex) {
              Logger.getLogger(WeyekinPoster.class.getName()).log(Level.SEVERE, null, ex);
          }
      } catch (URISyntaxException ex) {
          Logger.getLogger(WeyekinPoster.class.getName()).log(Level.SEVERE, null, ex);
      }
      
      if (!mPigeonString.isEmpty()) {
    	  return getPigeonURI();
      }
      return null;
    }
    
    private static URI getPigeonURI() {
        String[] split = mPigeonString.split("\n");
        for (String s : split) {
            if (s.contains(mPigeonImageIdentifier)) {
                try {
                    String path = mPigeonPath + s.substring(s.indexOf("img src =")+9, s.indexOf("alt =")-2);
                    return new URI(path);
//                    launchPage(mPigeonURI);
                } catch (URISyntaxException ex) {
                    Logger.getLogger(WeyekinPoster.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return null;
    }

    
    public static void postMyBird() {
        DefaultHttpClient httpclient = new DefaultHttpClient();
//        HttpPost httpPost = new HttpPost("http://128.197.164.27/pigeon1.php");
        HttpPost httpPost = new HttpPost(PIGEON_URL);
        List<NameValuePair> nvps = new ArrayList<NameValuePair>();
        nvps.add(new BasicNameValuePair("desc", mPigeonText));
        if (!mPigeonBackgroundColorHexString.isEmpty()) {
            nvps.add(new BasicNameValuePair("bgcolor", mPigeonBackgroundColorHexString));
        }
        try {
            httpPost.setEntity(new UrlEncodedFormEntity(nvps));

            try {
                HttpResponse response2 = httpclient.execute(httpPost);
                try {
//                    System.out.println(response2.getStatusLine());
                    HttpEntity entity2 = response2.getEntity();
//                    entity2.writeTo(System.out);
                    // do something useful with the response body
//                    System.out.println();
                    // and ensure it is fully consumed
                    EntityUtils.consume(entity2);
                } finally {
                    httpPost.releaseConnection();
                }
            } catch (ClientProtocolException ex) {
                Logger.getLogger(WeyekinPoster.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(WeyekinPoster.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(WeyekinPoster.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
//            httpPost.setURI(new URI("http://128.197.164.27/pigeon.php"));
            httpPost.setURI(new URI(PIGEON_URL));
            try {
                httpPost.setEntity(new UrlEncodedFormEntity(nvps));
                try {
                    HttpResponse response2 = httpclient.execute(httpPost);
                    try {
                        //System.out.println(response2.getStatusLine());
                        HttpEntity entity2 = response2.getEntity();
                        mPigeonString = EntityUtils.toString(entity2);
                        entity2.writeTo(System.out);
                        // do something useful with the response body
                        System.out.println();
                        // and ensure it is fully consumed
                        EntityUtils.consume(entity2);
                    } finally {
                        httpPost.releaseConnection();
                    }
                } catch (ClientProtocolException ex) {
                    Logger.getLogger(WeyekinPoster.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(WeyekinPoster.class.getName()).log(Level.SEVERE, null, ex);
                }
            } catch (UnsupportedEncodingException ex) {
                Logger.getLogger(WeyekinPoster.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (URISyntaxException ex) {
            Logger.getLogger(WeyekinPoster.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        if (!mPigeonString.isEmpty()) {
            parsePigeonResponse();
        }
    }
    
    private static void parsePigeonResponse() {
        String[] split = mPigeonString.split("\n");
        for (String s : split) {
            if (s.contains(mPigeonImageIdentifier)) {
                try {
                    String path = mPigeonPath + s.substring(s.indexOf("img src =")+9, s.indexOf("alt =")-2);
                    mPigeonURI = new URI(path);
                    launchPage(mPigeonURI);
                } catch (URISyntaxException ex) {
                    Logger.getLogger(WeyekinPoster.class.getName()).log(Level.SEVERE, null, ex);
                }
                return;
            }
        }
    }
    
    private static URI parseGraphVizResponse() {
        String[] split = mGraphVizString.split("\n");
        for (String s : split) {
            if (s.contains(mGraphVizImageIdentifier)) {
                try {
                    String path = mGraphVizPath + s.substring(s.indexOf("img src =")+9, s.indexOf("alt =")-1);
                    return new URI(path);
                } catch (URISyntaxException ex) {
                    Logger.getLogger(WeyekinPoster.class.getName()).log(Level.SEVERE, null, ex);
                }
                return null;
            }
        }
        return null;
    }

    public static void launchPage(URI pURI) {
        try {
            java.awt.Desktop.getDesktop().browse(pURI);
        } catch (IOException ex) {
            Logger.getLogger(WeyekinPoster.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static URI getmGraphVizURI() {
        return mGraphVizURI;
    }

    public static URI getmPigeonURI() {
        return mPigeonURI;
    }
    
    public static void setDotText(String text) {
        mGraphVizDotText = text;
    }
    
    /**
     * Aspect ratio ([0.0, 2.0])
     * Using major, minor, major, major
     * @param aratio 
     */
    public static void setWebGraphVizAspectRatioString(String aratio) {
        mGraphVizAspectRatioString = aratio;
    }
    
    public static void setPigeonText(String text) {
        mPigeonText = text;
    }
    
    /**
     * Background color:
     * If you assign #xyzwuv to the bgcolor variable in weyekin, then you can 
     * control the background color on pigeon images. 
     * xyzwuv should each be a digit between 0-9 or a letter between a-f or A-F
     * @param bgcolor 
     */
    public static void setPigeonBackgroundColorString(String bgcolor) {
        mPigeonBackgroundColorHexString = bgcolor;
    }
    
    
    /***
    @Deprecated
    public static void postMyCorrectVision() {
        DefaultHttpClient httpclient = new DefaultHttpClient();
        HttpPost httpPost = new HttpPost("http://128.197.164.27/weyekin/weyekin1.php");
        List<NameValuePair> nvps = new ArrayList<NameValuePair>();
        nvps.add(new BasicNameValuePair("gviz", "digraph { a -> b }"));
        try {
            httpPost.setEntity(new UrlEncodedFormEntity(nvps));

            try {
                HttpResponse response2 = httpclient.execute(httpPost);
                try {
                    //System.out.println(response2.getStatusLine());
                    HttpEntity entity2 = response2.getEntity();
                    entity2.writeTo(System.out);
                    // do something useful with the response body
                    System.out.println();
                    // and ensure it is fully consumed
                    EntityUtils.consume(entity2);
                } finally {
                    httpPost.releaseConnection();
                }
            } catch (ClientProtocolException ex) {
                Logger.getLogger(WeyekinPoster.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(WeyekinPoster.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(WeyekinPoster.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    ***/
}
