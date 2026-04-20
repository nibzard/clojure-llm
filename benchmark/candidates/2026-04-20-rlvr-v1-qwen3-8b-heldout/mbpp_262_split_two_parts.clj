(defn split_two_parts
  "	Write a function that takes in a list and an integer L and splits the given list into two parts where the length of the first part of the list is L, and returns the resulting lists in a vector."
  [list1 L]
  (let [v (vec list1)
        n (count v)]
    (if (or (zero? n) (neg? L) (> L n))
      [list1 []]
      [(seq (subvec v 0 L))
       (seq (subvec v L n))])))