package org.foo.spoke;

import java.lang.reflect.Method;
import org.foo.hub.Message;
import org.foo.hub.spi.Spoke;

/**
 * Spoke that audits matching messages using the hub's own Auditor class.
 */
public class SpokeImpl implements Spoke {

  String address;

  public SpokeImpl(String address) {
    this.address = address;
  }

  public boolean receive(Message message) {
    if (address.matches(message.getAddress())) {

      String auditorName = message.getClass().getPackage().getName() + ".Auditor";

      try {

        // attempt to load the hub's own Auditor class using the Thread Context ClassLoader
        Class auditClazz = Thread.currentThread().getContextClassLoader().loadClass(auditorName);
        Method method = auditClazz.getDeclaredMethod("audit", Spoke.class, Message.class);
        method.invoke(null, this, message);

        return true;

      } catch (Throwable e) {
        e.printStackTrace();
        return false;
      }
    }

    return false;
  }
}
