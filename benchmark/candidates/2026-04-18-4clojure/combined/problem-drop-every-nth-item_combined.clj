(defn problem-drop-every-nth-item [a b]
  (keep-indexed (fn [i x] (when (not= 0 (mod (inc i) b)) x)) a))

(require '[clojure.test :refer [deftest is run-tests]])

(deftest test-problem-drop-every-nth-item
  (is (= (= (problem-drop-every-nth-item [1 2 3 4 5 6 7 8] 3) [1 2 4 5 7 8])))
  (is (= (= (problem-drop-every-nth-item [:a :b :c :d :e :f] 2) [:a :c :e])))
  (is (= (= (problem-drop-every-nth-item [1 2 3 4 5 6] 4) [1 2 3 5 6]))))

(run-tests)
