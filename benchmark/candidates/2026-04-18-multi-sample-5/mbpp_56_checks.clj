(defn checks
  "	Write a cljthon function to check if a given number is one less than twice its reverse."
  [n]
  (let [sign (if (neg? n) -1 1)
        rev  (* sign
                (Long/parseLong
                 (apply str (reverse (seq (str (Math/abs (long n))))))))]
    (= n (dec (* 2 rev)))))