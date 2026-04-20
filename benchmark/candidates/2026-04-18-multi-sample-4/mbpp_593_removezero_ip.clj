(defn removezero_ip
  "	Write a function to remove leading zeroes from an ip address."
  [ip]
  (if (nil? ip)
    nil
    (->> (clojure.string/split (str ip) #"\.")
         (map #(str (Integer/parseInt %)))
         (clojure.string/join "."))))