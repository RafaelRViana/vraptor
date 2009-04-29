package br.com.caelum.vraptor.core;

import br.com.caelum.vraptor.resource.ResourceMethod;

/**
 * Holder for method being invoked and parameters being passed.
 * 
 * @author Guilherme Silveira
 * @author Fabio Kung
 */
public class DefaultMethodInfo implements MethodInfo {
	private ResourceMethod resourceMethod;
	private Object[] parameters;
	private String[] names;

	public ResourceMethod getResourceMethod() {
		return resourceMethod;
	}

	public void setResourceMethod(ResourceMethod resourceMethod) {
		this.resourceMethod = resourceMethod;
	}

	public void setParameters(Object[] parameters, String[] names) {
		this.parameters = parameters;
		this.names = names;
	}

	public Object[] getParameters() {
		return parameters;
	}

}
