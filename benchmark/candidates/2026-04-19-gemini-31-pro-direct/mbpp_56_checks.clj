(defn checks
  "Write a cljthon function to check if a given number is one less than twice its reverse."
  [n]
  (let [rev (Long/parseLong (apply str (reverse (str n))))]
    (= n (dec (* 2 rev)))))