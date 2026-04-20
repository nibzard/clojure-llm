(defn string_to_md5
  "Given a string 'text', return its md5 hash equivalent string.
  If 'text' is an empty string, return nil."
  [text]
  (when-not (empty? text)
    (let [md (java.security.MessageDigest/getInstance "MD5")
          bytes (.getBytes ^String text "UTF-8")
          digest (.digest md bytes)]
      (format "%032x" (BigInteger. 1 digest)))))