(defn smallest_change
  "Given a vector arr of integers, find the minimum number of elements that
  need to be changed to make the vector palindromic. A palindromic vector is a vector that
  is read the same backwards and forwards. In one change, you can change one element to any other element.
  For example:
  >>> (smallest_change [1 2 3 5 4 7 9 6])
  4
  >>> (smallest_change [1 2 3 4 3 2 2])
  1
  >>> (smallest_change [1 2 3 2 1])
  0"
  [arr]
  (let [n (count arr)]
    (reduce
      (fn [changes i]
        (if (= (nth arr i) (nth arr (- n 1 i)))
          changes
          (inc changes)))
      0
      (range (quot n 2)))))