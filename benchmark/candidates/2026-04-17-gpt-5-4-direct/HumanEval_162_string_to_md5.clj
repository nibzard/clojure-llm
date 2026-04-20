(defn string_to_md5
  "Given a string 'text', return its md5 hash equivalent string.
  If 'text' is an empty string, return nil.
  >>> (string_to_md5 \"Hello world\")
  \"3e25960a79dbc69b674cd4ec67a72c62\""
  [text]
  (when (seq text)
    (let [md (.digest (doto (java.security.MessageDigest/getInstance "MD5")
                        (.update (.getBytes text "UTF-8"))))]
      (apply str (map #(format "%02x" (bit-and % 0xff)) md)))))