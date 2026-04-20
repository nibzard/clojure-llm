(defn removezero_ip
  "	Write a function to remove leading zeroes from an ip address."
  [ip]
  (when ip
    (clojure.string/join
     "."
     (map (fn [part]
            (str (Long/parseLong (if (empty? part) "0" part))))
          (clojure.string/split ip #"\.")))))