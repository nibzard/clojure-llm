(defn correct_bracketing [brackets]
  (zero? (reduce (fn [cnt ch]
                   (let [cnt (case ch
                               \( (inc cnt)
                               \) (dec cnt)
                               cnt)]
                     (if (neg? cnt)
                       (reduced -1)
                       cnt)))
                 0
                 brackets)))