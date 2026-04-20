(defn divisible_by_digits
  "	Write a function to find numbers within a given range from startnum ti endnum where every number is divisible by every digit it contains. https://www.w3resource.com/cljthon-exercises/lambda/cljthon-lambda-exercise-24.php"
  [startnum endnum]
  (let [digits (fn [n]
                 (map #(Character/digit ^char % 10) (str (Math/abs (long n)))))
        divisible-by-own-digits? (fn [n]
                                   (every?
                                    (fn [d]
                                      (and (not (zero? d))
                                           (zero? (mod n d))))
                                    (digits n)))]
    (cond
      (or (nil? startnum) (nil? endnum)) '()
      (> startnum endnum) '()
      :else
      (filter divisible-by-own-digits? (range startnum (inc endnum))))))