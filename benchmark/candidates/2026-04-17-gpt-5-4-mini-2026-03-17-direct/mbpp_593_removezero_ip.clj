(defn removezero_ip
  "	Write a function to remove leading zeroes from an ip address."
  [ip]
  (when ip
    (clojure.string/join "."
                         (map #(str (Integer/parseInt %))
                              (clojure.string/split ip #"\.")))))