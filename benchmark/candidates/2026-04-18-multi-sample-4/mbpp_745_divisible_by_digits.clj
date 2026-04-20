(defn divisible_by_digits
  "	Write a function to find numbers within a given range from startnum ti endnum where every number is divisible by every digit it contains. https://www.w3resource.com/cljthon-exercises/lambda/cljthon-lambda-exercise-24.php"
  [startnum endnum]
  (let [digits-of (fn [n]
                    (map #(Character/digit ^char % 10) (str (Math/abs (long n)))))
        divisible-by-own-digits? (fn [n]
                                   (every? #(and (not (zero? %))
                                                 (zero? (mod n %)))
                                           (digits-of n)))
        [lo hi] (if (<= startnum endnum)
                  [startnum endnum]
                  [endnum startnum])]
    (filter divisible-by-own-digits? (range lo (inc hi)))))