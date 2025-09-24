package com.spcodage.constants;

public class AppConstants {

    public  static final  String STATUS_SUCCESS="SUCCESS";
    public  static final String STATUS_FAILED="FAILED";

       public  static  class StatusCodes{


           // 2xx Success
           public  static  final  int OK=200;
           public  static  final  int CREATED=201;
           public  static  final  int ACCEPTED=202;
           public  static  final  int NO_CONTENT=204;

           // 3xx Redirection
           public  static  final  int MOVED_PERMANENTLY=301;

           //4xx Client Error
           public  static  final int BAD_REQUEST=400;
           public  static  final int UNAUTHORIZED=401;
           public  static  final int FORBIDEEN=403;
           public  static  final int NOT_FOUND=404;
           public  static  final int METHOD_NOT_ALLOWED=405;
           public  static  final int  CONFLICT=409;

           //5xx Client Error
           public  static  final int INTERNAL_SERVER_ERROR=500;

       }

       public  static  class MessageConstant{
          // public static  final  String CREATED=(String entity) { return entity+ "created Successfully"; }
           public static  final  String CREATE="Created Succesfully";
           public static  final  String UPDATE="Updated Succesfully";
           public static  final  String DELETE="Delted  Succesfully";
           public static  final  String FETHCHED="Fetched  Succesfully";
           public static  final  String FETHCHE_ALL="All Users Fetched  Succesfully";
           public static  final  String LOGIN="Login Succesfully";
           public static  final  String FAIL="Login fAILED";

       }
}
