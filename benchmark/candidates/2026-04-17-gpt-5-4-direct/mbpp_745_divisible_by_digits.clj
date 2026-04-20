(defn divisible_by_digits
  "	Write a function to find numbers within a given range from startnum ti endnum where every number is divisible by every digit it contains. https://www.w3resource.com/cljthon-exercises/lambda/cljthon-lambda-exercise-24.php"
  [startnum endnum]
  (let [a (min startnum endnum)
        b (max startnum endnum)
        digits-of (fn [n]
                    (map #(Character/digit ^char % 10) (str (Math/abs (long n)))))
        divisible-by-all-digits? (fn [n]
                                   (let [ds (digits-of n)]
                                     (and (not-any? zero? ds)
                                          (every? #(zero? (mod n %)) ds))))]
    (filter divisible-by-all-digits? (range a (inc b)))))