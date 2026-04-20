(defn string_to_md5
  "Given a string 'text', return its md5 hash equivalent string.
  If 'text' is an empty string, return nil."
  [text]
  (when (seq text)
    (let [algorithm (java.security.MessageDigest/getInstance "MD5")
          raw-bytes (.digest algorithm (.getBytes text))]
      (apply str (map (partial format "%02x") raw-bytes)))))