/********* CDVTealiumPlugin.m Cordova Plugin Implementation *******/

#import "CDVTealiumPlugin.h"
#import <TealiumLibrary/Tealium.h>

@implementation CDVTealiumPlugin

- (void)track:(CDVInvokedUrlCommand*)command
{
    CDVPluginResult* pluginResult = nil;
    
    // Getting all parameters send from cordova webview
    NSDictionary* paramsDict = [command.arguments objectAtIndex:0];
    NSString* callType = [command.arguments objectAtIndex:1];
    
   
    //deafault call type is "link"
    NSString * tealiumCallType = TealiumEventCall;

    
    @try {

        // If call type is set to "view"
        if([callType isEqualToString:@"view"]){
            tealiumCallType = TealiumViewCall;
        }

        // If no param send
        if(paramsDict == nil){
            [[Tealium sharedInstance] track:self.viewController customData:nil as:tealiumCallType];
            NSLog(@"Tealium tarck called without any data");
            
            pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK messageAsString:@"Tealium tarck called without any data"];
        }
        
        // If there is any params
        else if (paramsDict != nil && [paramsDict count] > 0) {
            [[Tealium sharedInstance] track:self.viewController customData:paramsDict as:tealiumCallType];
            NSLog(@"Tealium tarck called with info %@", paramsDict);
            
            pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK messageAsString:[NSString stringWithFormat:@"%@", paramsDict]];
        }
    }

    @catch (NSException *exception) {
        pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_ERROR];
        NSLog(@"Exception: %@", exception);
    }

    @finally {
        [self.commandDelegate sendPluginResult:pluginResult callbackId:command.callbackId];
    }
 

}

- (void)addGlobalCustomData:(CDVInvokedUrlCommand*)command
{
    CDVPluginResult* pluginResult = nil;
    
    // Getting all parameters send from cordova webview
    NSDictionary* params = [command.arguments objectAtIndex:0];
    
    
    @try {
        
        
    }
    
    @catch (NSException *exception) {
        pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_ERROR];
        NSLog(@"Exception: %@", exception);
    }
    
    @finally {
        [self.commandDelegate sendPluginResult:pluginResult callbackId:command.callbackId];
    }
    
}

- (void)addCustomData:(CDVInvokedUrlCommand*)command
{
    CDVPluginResult* pluginResult = nil;
    
    // Getting all parameters send from cordova webview
    NSDictionary* params = [command.arguments objectAtIndex:0];
    
    
    @try {
        
        
    }
    
    @catch (NSException *exception) {
        pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_ERROR];
        NSLog(@"Exception: %@", exception);
    }
    
    @finally {
        [self.commandDelegate sendPluginResult:pluginResult callbackId:command.callbackId];
    }
    
}





@end
