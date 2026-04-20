(defn removezero_ip
  "	Write a function to remove leading zeroes from an ip address."
  [ip]
  (if (nil? ip)
    nil
    (clojure.string/join
     "."
     (map #(str (Long/parseLong %))
          (clojure.string/split ip #"\.")))))