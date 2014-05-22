/********* CDVTealiumPlugin.h Cordova Plugin Implementation *******/

#import <Cordova/CDV.h>

@interface CDVTealiumPlugin : CDVPlugin {
    // Member variables go here.
}

- (void)track:(CDVInvokedUrlCommand*)command;
- (void)addGlobalCustomData:(CDVInvokedUrlCommand*)command;
- (void)addCustomData:(CDVInvokedUrlCommand*)command;


@end
