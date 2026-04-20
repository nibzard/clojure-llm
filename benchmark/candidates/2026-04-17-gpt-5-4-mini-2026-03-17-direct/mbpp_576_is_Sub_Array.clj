(defn is_Sub_Array
  "	Write a cljthon function to check whether a list is sublist of another or not."
  [A B]
  (let [a (vec A)
        b (vec B)
        la (count a)
        lb (count b)]
    (cond
      (zero? lb) true
      (< la lb) false
      :else (boolean
             (some true?
                   (for [i (range (inc (- la lb)))]
                     (= (subvec a i (+ i lb)) b)))))))