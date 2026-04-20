(defn checks
  "	Write a cljthon function to check if a given number is one less than twice its reverse."
  [n]
  (let [s (str (Math/abs (long n)))
        rev (Long/parseLong (apply str (reverse s)))]
    (= (Math/abs (long n)) (dec (* 2 rev)))))