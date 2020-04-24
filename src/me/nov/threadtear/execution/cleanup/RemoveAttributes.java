package me.nov.threadtear.execution.cleanup;

import java.util.List;
import java.util.Map;

import me.nov.threadtear.execution.Execution;
import me.nov.threadtear.execution.ExecutionCategory;
import me.nov.threadtear.execution.ExecutionTag;
import me.nov.threadtear.io.Clazz;

public class RemoveAttributes extends Execution {

	public RemoveAttributes() {
		super(ExecutionCategory.CLEANING, "Remove attributes", "Removes local variable names and signatures", ExecutionTag.SHRINK, ExecutionTag.BETTER_DECOMPILE);
	}

	@Override
	public boolean execute(Map<String, Clazz> classes, boolean verbose) {
		classes.values().stream().map(c -> c.node).forEach(c -> {
			c.signature = null;
		});
		classes.values().stream().map(c -> c.node.methods).flatMap(List::stream).forEach(m -> {
			m.localVariables = null;
			m.signature = null;
		});
		classes.values().stream().map(c -> c.node.fields).flatMap(List::stream).forEach(f -> {
			f.signature = null;
		});
		logger.info("Removed all local variable names and generic attributes");
		return true;
	}
}