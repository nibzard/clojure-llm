(defn sum_Of_Subarray_Prod
  [arr]
  (first
   (reduce (fn [[ans cur] x]
             (let [new-cur (* (inc cur) x)]
               [(+ ans new-cur) new-cur]))
           [0 0]
           arr)))