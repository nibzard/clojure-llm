(defn normalize-ip
  "Remove leading zeroes from each octet in an IPv4 address string.

  Examples:
  (normalize-ip \"192.168.001.010\") => \"192.168.1.10\"
  (normalize-ip \"010.000.000.001\") => \"10.0.0.1\"
  (normalize-ip nil) => nil"
  [ip]
  (when ip
    (->> (clojure.string/split ip #"\.")
         (map #(str (Integer/parseInt %)))
         (clojure.string/join "."))))