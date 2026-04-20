(defn problem-word-chains [a]
  (letfn [(one-away? [w1 w2]
            (let [l1 (count w1)
                  l2 (count w2)]
              (cond
                (> (Math/abs (- l1 l2)) 1) false
                (= l1 l2)
                (= 1 (count (filter false? (map = w1 w2))))
                (= l1 (inc l2))
                (loop [s1 (seq w1) s2 (seq w2) skipped false]
                  (cond
                    (nil? s2) true
                    (nil? s1) false
                    (= (first s1) (first s2)) (recur (next s1) (next s2) skipped)
                    skipped false
                    :else (recur (next s1) s2 true)))
                (= l2 (inc l1))
                (one-away? w2 w1)
                :else false)))
          (chain? [remaining prev]
            (if (empty? remaining)
              true
              (boolean
               (some #(and (one-away? prev %)
                           (chain? (disj remaining %) %))
                     remaining))))]
    (or (empty? a)
        (boolean
         (some #(chain? (disj a %) %) a)))))

(require '[clojure.test :refer [deftest is run-tests]])

(deftest test-problem-word-chains
  (is (= (= true (problem-word-chains #{"hat" "coat" "dog" "cat" "oat" "cot" "hot" "hog"}))))
  (is (= (= false (problem-word-chains #{"cot" "hot" "bat" "fat"}))))
  (is (= (= false (problem-word-chains #{"to" "top" "stop" "tops" "toss"}))))
  (is (= (= true (problem-word-chains #{"spout" "do" "pot" "pout" "spot" "dot"}))))
  (is (= (= true (problem-word-chains #{"share" "hares" "shares" "hare" "are"}))))
  (is (= (= false (problem-word-chains #{"share" "hares" "hare" "are"})))))

(run-tests)
