(defn longest-prefix
  "Given a collection of strings, return the longest common prefix.

  If the collection is empty, return nil.
  If there is no shared prefix, return the empty string.

  Examples:
  (longest-prefix [\"flower\" \"flow\" \"flight\"]) ;=> \"fl\"
  (longest-prefix [\"dog\" \"racecar\" \"car\"])    ;=> \"\"
  (longest-prefix [])                             ;=> nil"
  [strings]
  (when (seq strings)
    (let [prefix (first strings)]
      (loop [i 0]
        (if (or (= i (count prefix))
                (some #(not= (nth prefix i) (nth % i nil)) (rest strings)))
          (subs prefix 0 i)
          (recur (inc i)))))))

(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= "fl" (longest-prefix ["flower" "flow" "flight"]))))

(run-test test-variation)
