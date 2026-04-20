(defn checks
  "	Write a cljthon function to check if a given number is one less than twice its reverse."
  [n]
  (let [sign (if (neg? n) -1 1)
        rev  (->> (Math/abs (long n))
                  str
                  reverse
                  (apply str)
                  Long/parseLong
                  (* sign))]
    (= n (dec (* 2 rev)))))