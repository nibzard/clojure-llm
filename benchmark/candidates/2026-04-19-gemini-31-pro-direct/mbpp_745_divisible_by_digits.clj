(defn divisible_by_digits
  "Write a function to find numbers within a given range from startnum to endnum where every number is divisible by every digit it contains."
  [startnum endnum]
  (let [valid? (fn [n]
                 (let [digits (->> (str (Math/abs n))
                                   (map #(Character/digit % 10)))]
                   (and (not-any? zero? digits)
                        (every? #(zero? (mod n %)) digits))))]
    (filter valid? (range startnum (inc endnum)))))