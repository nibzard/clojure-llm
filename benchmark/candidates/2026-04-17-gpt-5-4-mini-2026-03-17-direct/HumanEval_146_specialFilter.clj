(defn specialFilter
  [nums]
  (letfn [(odd-digit? [c]
            (contains? #{\1 \3 \5 \7 \9} c))
          (special? [n]
            (let [s (str (Math/abs (long n)))]
              (and (> n 10)
                   (odd-digit? (first s))
                   (odd-digit? (last s)))))]
    (count (filter special? nums))))