(defn encrypt
  [s]
  (let [shift (* 2 2)]
    (apply str
           (map (fn [c]
                  (if (Character/isLetter c)
                    (let [base (int (if (Character/isUpperCase c) \A \a))
                          offset (- (int c) base)
                          new-offset (mod (+ offset shift) 26)]
                      (char (+ base new-offset)))
                    c))
                s))))