(defn rearrange_bigger
  "Write a function to create the next bigger number by rearranging the digits of a given number."
  [n]
  (let [digits (vec (str n))
        len (count digits)]
    (loop [i (dec len)]
      (if (neg? i)
        nil
        (let [current-digit (digits i)
              ;; Find the smallest digit to the right of i that is greater than current-digit
              right-digits (subvec digits (inc i))
              candidates (filter #(> % current-digit) right-digits)
              min-greater (when (seq candidates) (apply min candidates))]
          (if min-greater
            ;; Find the index of the rightmost occurrence of min-greater to ensure stability
            ;; and minimal increase
            (let [j (+ (inc i) (.lastIndexOf right-digits min-greater))
                  ;; Construct the new number
                  prefix (subvec digits 0 i)
                  middle (subvec digits (inc i) j)
                  suffix (subvec digits (inc j))
                  new-digits (into [] (concat prefix [min-greater] middle suffix [(digits j)]))]
              (read-string (apply str new-digits)))
            (recur (dec i))))))))