(defn find-shortest-subseq
  "Return the shortest contiguous subsequence of `coll` that contains all distinct
  elements appearing in `coll`.

  Returns nil for nil or empty input.

  Examples:
  (find-shortest-subseq [1 2 1 3 2]) ;=> [1 3 2]
  (find-shortest-subseq [:a :b :a :c :b :c]) ;=> [:a :c :b :c]"
  [coll]
  (when (seq coll)
    (let [target-count (count (set coll))
          n (count coll)]
      (loop [start 0
             end 0
             freq {}
             best nil]
        (cond
          (= target-count (count freq))
          (let [candidate (subvec (vec coll) start end)
                best (if (or (nil? best)
                            (< (count candidate) (count best)))
                       candidate
                       best)]
            (if (< start n)
              (let [x (nth coll start)
                    freq (update freq x (fnil dec 0))
                    freq (if (zero? (get freq x)) (dissoc freq x) freq)]
                (recur (inc start) end freq best))
              best))

          (< end n)
          (let [x (nth coll end)]
            (recur start
                   (inc end)
                   (update freq x (fnil inc 0))
                   best))

          :else
          best)))))

(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= [1 3 2] (find-shortest-subseq [1 2 1 3 2])))
  (is (= [:a :c :b :c] (find-shortest-subseq [:a :b :a :c :b :c])))
  (is (nil? (find-shortest-subseq []))))

(run-test test-variation)
