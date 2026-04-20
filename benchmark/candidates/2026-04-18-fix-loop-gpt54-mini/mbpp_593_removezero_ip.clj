(defn removezero_ip
  "	Write a function to remove leading zeroes from an ip address."
  [ip]
  (->> (clojure.string/split ip #"\.")
       (map #(str (Integer/parseInt %)))
       (clojure.string/join ".")))