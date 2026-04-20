(defn removezero_ip
  "Write a function to remove leading zeroes from an ip address."
  [ip]
  (if (nil? ip)
    nil
    (->> (clojure.string/split (str ip) #"\." -1)
         (map #(str (Long/parseLong %)))
         (clojure.string/join "."))))