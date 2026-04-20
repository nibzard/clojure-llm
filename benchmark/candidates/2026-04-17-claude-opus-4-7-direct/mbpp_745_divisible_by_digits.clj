(defn divisible_by_digits
  "Write a function to find numbers within a given range from startnum to endnum where every number is divisible by every digit it contains."
  [startnum endnum]
  (letfn [(divisible-by-all? [n]
            (let [digits (map #(Character/digit % 10) (str n))]
              (every? #(or (zero? %) (zero? (mod n %))) digits)))]
    (filter divisible-by-all? (range startnum (inc endnum)))))