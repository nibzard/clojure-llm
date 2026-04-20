(defn max-cumulative-drop
  "Return the maximum cumulative drop in a sequence of numbers.

  The cumulative drop is the largest positive difference between a value
  and a later smaller value, considering only the order of the sequence.

  Examples:
  (max-cumulative-drop [10 7 5 8 3]) ;=> 7  ; 10 -> 3
  (max-cumulative-drop [5 4 3 2])    ;=> 3  ; 5 -> 2
  (max-cumulative-drop [1 2 3])      ;=> 0  ; no drop
  (max-cumulative-drop [])           ;=> 0"
  [nums]
  (loop [xs (seq nums)
         max-so-far nil
         best-drop 0]
    (if-let [x (first xs)]
      (let [max-so-far (if (nil? max-so-far) x (max max-so-far x))
            drop (if (nil? max-so-far) 0 (- max-so-far x))
            best-drop (max best-drop drop)]
        (recur (next xs) max-so-far best-drop))
      best-drop)))

(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= 7 (max-cumulative-drop [10 7 5 8 3]))))

(run-test test-variation)
