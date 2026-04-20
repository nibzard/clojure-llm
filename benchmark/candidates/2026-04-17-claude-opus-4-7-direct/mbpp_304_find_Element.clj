(defn find_Element
  "Write a cljthon function to find element at a given index after number of rotations."
  [arr ranges rotations index]
  (let [n (count arr)
        ;; Calculate the effective number of rotations
        ;; (ranges - 1) represents the number of full cycles
        ;; We use mod to handle cases where rotations > ranges
        effective-rotations (mod (* rotations (dec ranges)) n)
        ;; Calculate the new index after rotation
        ;; If we rotate left k times, the element at original index i 
        ;; moves to (i - k) mod n
        new-index (mod (- index effective-rotations) n)]
    (get arr new-index)))