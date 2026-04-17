#!/usr/bin/env bash
# run-demo.sh - Main demo script for clojure-llm benchmark evaluation
#
# This script runs all benchmark conditions and generates comparison tables.
#
# Conditions:
#   A: Opus via API, direct generation, no tools
#   B: GPT-5.4 via API, direct generation, no tools
#   C: Qwen3-8B-Base, direct generation, no tools
#   D: Qwen3-8B + SFT + RLVR with clj-verifier tools, agent loop
#
# Usage:
#   ./demo/run-demo.sh [OPTIONS]
#
# Options:
#   -a, --condition-a   Run only condition A
#   -b, --condition-b   Run only condition B
#   -c, --condition-c   Run only condition C
#   -d, --condition-d   Run only condition D
#   -n, --dry-run       Show what would be done without executing
#   -h, --help          Show this help message
#
# Examples:
#   ./demo/run-demo.sh              # Run all conditions
#   ./demo/run-demo.sh -a -b        # Run only conditions A and B
#   ./demo/run-demo.sh -n           # Dry run all conditions
#
# Output:
#   - Run manifests: benchmark/runs/
#   - Results:       demo/results/
#   - Tables:        stdout

set -euo pipefail

# Configuration
SCRIPT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"
PROJECT_ROOT="$(cd "${SCRIPT_DIR}/.." && pwd)"
RESULTS_DIR="${PROJECT_ROOT}/demo/results"
RUNS_DIR="${PROJECT_ROOT}/benchmark/runs"

# Colors for output
RED='\033[0;31m'
GREEN='\033[0;32m'
YELLOW='\033[0;33m'
BLUE='\033[0;34m'
NC='\033[0m' # No Color

# Default: run all conditions
RUN_A=false
RUN_B=false
RUN_C=false
RUN_D=false
DRY_RUN=false

# Parse command line arguments
while [[ $# -gt 0 ]]; do
    case $1 in
        -a|--condition-a)
            RUN_A=true
            shift
            ;;
        -b|--condition-b)
            RUN_B=true
            shift
            ;;
        -c|--condition-c)
            RUN_C=true
            shift
            ;;
        -d|--condition-d)
            RUN_D=true
            shift
            ;;
        -n|--dry-run)
            DRY_RUN=true
            shift
            ;;
        -h|--help)
            show_help
            exit 0
            ;;
        *)
            echo -e "${RED}Error: Unknown option: $1${NC}"
            show_help
            exit 1
            ;;
    esac
done

# If no specific conditions selected, run all
if [[ "$RUN_A" == false && "$RUN_B" == false && "$RUN_C" == false && "$RUN_D" == false ]]; then
    RUN_A=true
    RUN_B=true
    RUN_C=true
    RUN_D=true
fi

# Logging functions
log_info() {
    echo -e "${BLUE}[INFO]${NC} $1"
}

log_success() {
    echo -e "${GREEN}[SUCCESS]${NC} $1"
}

log_warning() {
    echo -e "${YELLOW}[WARNING]${NC} $1"
}

log_error() {
    echo -e "${RED}[ERROR]${NC} $1"
}

# Show help message
show_help() {
    cat << EOF
Usage: $0 [OPTIONS]

Run benchmark evaluation conditions and generate comparison tables.

Conditions:
  A: Opus via API, direct generation, no tools
  B: GPT-5.4 via API, direct generation, no tools
  C: Qwen3-8B-Base, direct generation, no tools
  D: Qwen3-8B + SFT + RLVR with clj-verifier tools, agent loop

Options:
  -a, --condition-a   Run only condition A
  -b, --condition-b   Run only condition B
  -c, --condition-c   Run only condition C
  -d, --condition-d   Run only condition D
  -n, --dry-run       Show what would be done without executing
  -h, --help          Show this help message

Examples:
  $0                    # Run all conditions
  $0 -a -b              # Run only conditions A and B
  $0 -n                 # Dry run all conditions

Output:
  - Run manifests: benchmark/runs/
  - Results:       demo/results/
  - Tables:        stdout
EOF
}

# Generate run manifest for a condition
generate_manifest() {
    local run_id="$1"
    local model_id="$2"
    local policy_kind="$3"

    log_info "Generating manifest for condition: $run_id"
    log_info "  Model: $model_id"
    log_info "  Policy: $policy_kind"

    if [[ "$DRY_RUN" == true ]]; then
        echo "    [DRY RUN] Would create: ${RUNS_DIR}/${run_id}.edn"
        return
    fi

    cd "$PROJECT_ROOT"
    clojure -M:bench plan-run "$run_id" "$model_id" "$policy_kind"

    log_success "Created manifest: ${RUNS_DIR}/${run_id}.edn"
}

# Execute a run manifest (placeholder - actual executor not yet implemented)
execute_run() {
    local run_id="$1"

    log_info "Executing run: $run_id"

    if [[ "$DRY_RUN" == true ]]; then
        echo "    [DRY RUN] Would execute run: $run_id"
        echo "    [DRY RUN] Would write results to: ${RESULTS_DIR}/${run_id}/"
        return
    fi

    # Create results directory
    mkdir -p "${RESULTS_DIR}/${run_id}"

    # TODO: Implement actual executor
    # This would:
    # 1. Load the run manifest from ${RUNS_DIR}/${run_id}.edn
    # 2. Execute tasks against the specified model
    # 3. Write result EDN files to ${RESULTS_DIR}/${run_id}/

    log_warning "Executor not yet implemented. Results directory created: ${RESULTS_DIR}/${run_id}/"
}

# Aggregate results and print comparison table
aggregate_results() {
    local run_ids=("$@")

    log_info "Aggregating results for ${#run_ids[@]} runs"

    if [[ "$DRY_RUN" == true ]]; then
        echo "    [DRY RUN] Would aggregate: ${run_ids[*]}"
        return
    fi

    cd "$PROJECT_ROOT"

    # Check if results exist
    local missing_runs=()
    for run_id in "${run_ids[@]}"; do
        if [[ ! -d "${RESULTS_DIR}/${run_id}" ]]; then
            missing_runs+=("$run_id")
        fi
    done

    if [[ ${#missing_runs[@]} -gt 0 ]]; then
        log_warning "Results not found for runs: ${missing_runs[*]}"
        log_warning "Skipping aggregation."
        return
    fi

    clojure -M:bench aggregate "${run_ids[@]}"
}

# Main execution flow
main() {
    echo "=================================="
    echo "  clojure-llm Demo Runner"
    echo "=================================="
    echo ""

    # Track run IDs for aggregation
    local run_ids=()

    # Condition A: Opus via API, direct generation, no tools
    if [[ "$RUN_A" == true ]]; then
        echo ""
        echo "----------------------------------------"
        echo "Condition A: Opus (API, direct, no tools)"
        echo "----------------------------------------"
        local run_id="condition-a-opus-direct"
        generate_manifest "$run_id" "claude-opus-4.6" "direct"
        execute_run "$run_id"
        run_ids+=("$run_id")
    fi

    # Condition B: GPT-5.4 via API, direct generation, no tools
    if [[ "$RUN_B" == true ]]; then
        echo ""
        echo "----------------------------------------"
        echo "Condition B: GPT-5.4 (API, direct, no tools)"
        echo "----------------------------------------"
        local run_id="condition-b-gpt54-direct"
        generate_manifest "$run_id" "gpt-5.4" "direct"
        execute_run "$run_id"
        run_ids+=("$run_id")
    fi

    # Condition C: Qwen3-8B-Base, direct generation, no tools
    if [[ "$RUN_C" == true ]]; then
        echo ""
        echo "----------------------------------------"
        echo "Condition C: Qwen3-8B-Base (direct, no tools)"
        echo "----------------------------------------"
        local run_id="condition-c-qwen38b-base-direct"
        generate_manifest "$run_id" "Qwen/Qwen3-8B-Base" "direct"
        execute_run "$run_id"
        run_ids+=("$run_id")
    fi

    # Condition D: Qwen3-8B + SFT + RLVR with clj-verifier tools, agent loop
    if [[ "$RUN_D" == true ]]; then
        echo ""
        echo "----------------------------------------"
        echo "Condition D: Qwen3-8B + SFT + RLVR (agent with tools)"
        echo "----------------------------------------"
        local run_id="condition-d-qwen38b-sft-rlvr-agent"
        log_info "Generating manifest for condition: $run_id"
        log_info "  Model: Qwen/Qwen3-8B-Instruct"
        log_info "  Policy: agent (with clj-verifier tools)"

        if [[ "$DRY_RUN" == true ]]; then
            echo "    [DRY RUN] Would create: ${RUNS_DIR}/${run_id}.edn"
        else
            # Condition D uses a custom agent policy with tools
            # This requires a special run plan with retries and tools enabled
            cd "$PROJECT_ROOT"

            # Create a custom manifest for the agent condition
            local manifest_file="${RUNS_DIR}/${run_id}.edn"
            local timestamp=$(date -u +"%Y-%m-%dT%H:%M:%SZ")

            # Get task IDs from benchmark list
            local task_ids=$(cd "$PROJECT_ROOT" && clojure -M:bench list 2>/dev/null | awk '{print "\""$1"\""}' | tr '\n' ' ' | sed 's/,$//')

            cat > "$manifest_file" << EOF
{:run-id "${run_id}"
 :benchmark-version :clj-bench/v0
 :created-at "${timestamp}"
 :model {:provider :hf
         :id "Qwen/Qwen3-8B-Instruct"}
 :prompting {:template :agent-v1
             :temperature 0.2
             :top-p 0.95
             :samples 1}
 :policy {:kind :agent
          :retries 3
          :clj-kondo true
          :repl false
          :grammar :none}
 :tasks-file "benchmark/tasks-v0.edn"
 :task-ids [${task_ids}]
 :executor {:kind :container
            :image "clj-bench/eval:dev"
            :network :none}}
EOF
            log_success "Created manifest: ${manifest_file}"
        fi

        execute_run "$run_id"
        run_ids+=("$run_id")
    fi

    # Aggregate and compare results
    if [[ ${#run_ids[@]} -gt 0 ]]; then
        echo ""
        echo "=================================="
        echo "  Result Aggregation"
        echo "=================================="
        aggregate_results "${run_ids[@]}"
    fi

    echo ""
    log_success "Demo complete!"
}

# Run main function
main "$@"
