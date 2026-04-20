(defn divisible_by_digits
  "Write a function to find numbers within a given range from startnum to endnum where every number is divisible by every digit it contains."
  [startnum endnum]
  (let [digits (fn [n]
                 (map #(Character/digit ^char % 10) (str (Math/abs (long n)))))
        divisible? (fn [n]
                     (every? #(and (not (zero? %)) (zero? (mod n %)))
                             (digits n)))]
    (filter divisible? (range startnum (inc endnum)))))