(defn unique_digits
  "Given a list of positive integers x. return a sorted list of all 
  elements that hasn't any even digit.
  Note: Returned list should be sorted in increasing order.
  For example:
  >>> (unique_digits [15 33 1422 1])
  [1 15 33]
  >>> (unique_digits [152 323 1422 10])
  []"
  [x]
  (letfn [(all-odd-digits? [n]
            (every? (fn [ch]
                      (odd? (- (int ch) (int \0))))
                    (str n)))]
    (->> x
         (filter all-odd-digits?)
         sort
         vec)))