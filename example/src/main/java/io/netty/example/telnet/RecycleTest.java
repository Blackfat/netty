package io.netty.example.telnet;

import io.netty.util.Recycler;

/**
 * @author blackfat
 * @create 2019-04-09-上午6:37
 */
public class RecycleTest {

    private static final Recycler<User> RECYCLER = new Recycler<User>() {
        @Override
        protected User newObject(Handle<User> handle) {
            return new User(handle);
        }
    };

    private static class User{
        private final Recycler.Handle<User> handle;

        public User(Recycler.Handle<User> handle){
            this.handle = handle;
        }

        public void recycle(){
            handle.recycle(this);
        }
    }

    public static void main(String[] args) {
        User user = RECYCLER.get();

        user.recycle();

        User user1 = RECYCLER.get();

        System.out.println(user == user1);
    }


}
