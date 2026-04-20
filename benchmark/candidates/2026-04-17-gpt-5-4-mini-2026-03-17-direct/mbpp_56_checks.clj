(defn checks
  "Write a cljthon function to check if a given number is one less than twice its reverse."
  [n]
  (= n (dec (* 2 (Long/parseLong (apply str (reverse (str n))))))))