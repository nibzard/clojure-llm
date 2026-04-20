(defn move_one_ball
  [arr]
  (if (empty? arr)
    true
    (let [cnt (count arr)
          ;; Create a sequence of all possible rotations
          rotations (for [i (range cnt)]
                      (take cnt (drop i (cycle arr))))]
      ;; Check if any rotation is sorted in non-decreasing order
      (some #(apply <= %) rotations))))