(defn string_to_md5
  "Given a string 'text', return its md5 hash equivalent string.
  If 'text' is an empty string, return nil."
  [text]
  (when (seq text)
    (let [md5 (java.security.MessageDigest/getInstance "MD5")
          bytes (.digest md5 (.getBytes (str text) "UTF-8"))]
      (apply str (map #(format "%02x" (bit-and % 0xff)) bytes)))))