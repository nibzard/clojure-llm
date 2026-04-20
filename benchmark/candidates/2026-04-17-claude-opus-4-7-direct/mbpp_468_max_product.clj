(defn max_product
  "Write a function to find the maximum product formed by multiplying numbers of an increasing subsequence of that vector."
  [arr]
  (if (empty? arr)
    0
    (let [n (count arr)
          ;; Initialize DP table: dp[i] stores the max product of an increasing subsequence ending at index i
          ;; Initialize with 1s so we can multiply, but we will handle the "single element" case logic
          dp (long-array n 1)]
      ;; Fill DP table
      (doseq [i (range 1 n)]
        (let [current-val (nth arr i)]
          (doseq [j (range i)]
            (when (< (nth arr j) current-val)
              (let [candidate (* (aget dp j) current-val)]
                (when (> candidate (aget dp i))
                  (aset dp i candidate)))))))
      
      ;; The result is the maximum value in the DP array.
      ;; However, if the array contains only 1s or decreasing numbers, 
      ;; the max product is simply the maximum element in the array.
      ;; Since we initialized dp with 1s, we need to compare against the actual max element.
      (let [max-dp (reduce max 1 dp)
            max-elem (reduce max (long-array arr))]
        (max max-dp max-elem)))))