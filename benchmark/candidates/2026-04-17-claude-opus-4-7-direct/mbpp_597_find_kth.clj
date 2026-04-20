(defn find_kth
  "Write a function to find kth element from the given two sorted vectors."
  [arr1 arr2 k]
  (let [cnt1 (count arr1)
        cnt2 (count arr2)
        total (+ cnt1 cnt2)]
    (cond
      ;; Handle invalid k (1-based index)
      (or (< k 1) (> k total) (and (zero? cnt1) (zero? cnt2)) nil?
        (throw (ex-info "k is out of bounds" {:k k :total total}))
      
      ;; If arr1 is empty, return kth element from arr2
      (zero? cnt1)
      (nth arr2 (dec k))
      
      ;; If arr2 is empty, return kth element from arr1
      (zero? cnt2)
      (nth arr1 (dec k))
      
      ;; If k is 1, return the minimum of the two heads
      (= 1 k)
      (min (first arr1) (first arr2))
      
      :else
      (let [;; Calculate split points for k/2
            half (quot k 2)
            idx1 (min half cnt1)
            idx2 (min half cnt2)
            val1 (nth arr1 (dec idx1))
            val2 (nth arr2 (dec idx2))]
        (if (<= val1 val2)
          ;; Discard first idx1 elements of arr1
          (recur (subvec arr1 idx1) arr2 (- k idx1))
          ;; Discard first idx2 elements of arr2
          (recur arr1 (subvec arr2 idx2) (- k idx2)))))))