(defn removezero_ip
  "Write a function to remove leading zeroes from an ip address."
  [ip]
  (when ip
    (clojure.string/join "."
      (map #(clojure.string/replace-first #"^0+" "") (clojure.string/split ip #"\.")))))